#!/bin/bash
scriptdir=$(dirname $(realpath $0))
src_script="$scriptdir/pkgwhat.clj"
if test -z "$1"
then
    targetbin=$HOME/bin
else
    targetbin=$1
fi
targ_pkgwhat=$targetbin/pkgwhat

cat <<EOF > $targ_pkgwhat
#!/usr/bin/env bash
$src_script \$(basename \$0) \$@
EOF

chmod 755 $targ_pkgwhat

(cd $targetbin
 ln -s pkgwhat which-package-has-file
 ln -s pkgwhat installed-packages
 ln -s pkgwhat files-in-package
 ln -s pkgwhat package-info)
