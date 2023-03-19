#!/usr/bin/env bb
(ns pkgwhat
  (:require [clojure.java.io :as io]
            [clojure.java.shell :as shell]
            [clojure.string :as str]))


(def oslikes
  {
   "ubuntu debian" {
                    "which-package-has-file" ["dpkg" "--search" :argument]
                    "installed-packages"     ["dpkg" "-l"]
                    "files-in-package"       ["dpkg" "-L" :argument]
                    "package-info"           ["apt-cache" "show" :argument]
                    }
   "rhel fedora" {
                  "which-package-has-file" ["repoquery" "--installed" "-f" :argument]
                  "installed-packages"     ["yum" "list" "installed"]
                  "files-in-package"       ["repoquery" "--installed" "-l" :argument]
                  "package-info"           ["yum" "info" :argument]
                  }
   "arch" {
           "which-package-has-file" ["pacman" "-Qo" :argument]
           "installed-packages"     ["pacman" "-Q"]
           "explicit-packages"      ["pacman" "-Qet"]
           "files-in-package"       ["pacman" "-Ql" :argument]
           "package-info"           ["pacman" "-Si" :argument]
           }
   "alpine" {
             "which-package-has-file" ["apk" "info" "--who-owns" :argument]
             "installed-packages"     ["apk" "list"]
             "files-in-package"       ["apk" "info" "--contents" :argument]
             "package-info"           ["apk" "info" :argument]
             
             }
   })


(defn trimquotes [s]
  (if (= (first (seq s)) \")
    (subs s 1 (- (count s) 1))
    s))


(defn get-os-id [varname]
  (with-open [os-release-file (io/reader "/etc/os-release")]
    (trimquotes
     (first (seq
             (for [line (line-seq os-release-file)
                   :when (str/starts-with? line varname)]
               (str/trim (last (str/split line #"=" 2)))))))))

(defn get-os-release []
  (let [idlike (get-os-id "ID_LIKE=")]
    (if (nil? idlike)
      (get-os-id "ID=")
      idlike)))

(let [query-cmd (first *command-line-args*)
      sarg (first (rest *command-line-args*))
      shell-command (replace {:argument sarg}
                             ((oslikes (get-os-release))
                              query-cmd))]
  (println (:out (apply shell/sh shell-command))))
