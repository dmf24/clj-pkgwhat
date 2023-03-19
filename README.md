## clj-pkgwhat

This is a trivial babashka script to resolve common package manager queries from standard names.

This will install a script along with symlinks to $HOME/bin

```
git clone https://github.com/dmf24/clj-pkgwhat.git
cd clj-pkgwhat
./install.sh
```

To install to /usr/local/bin

```
sudo ./install /usr/local/bin
```

### which-package-has-file

This accepts a path argument and finds which package owns the given file.

### installed-packages

This lists all installed packages known by the package manager.

### files-in-package

This lists all files in the specified package.

### package-info

This shows detailed information about the package.
