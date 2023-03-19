#!/bin/bash
set -e

# usage:  install.sh [/path/to/desired/bin]
# if path is not specified, defaults to $HOME/bin
#
# This script will install pkgwhat.clj, create a trivial
# bash wrapper named pkgwhat, with commands symlinked to
# the wrapper.

scriptdir=$(dirname $(realpath $0))
src_script="$scriptdir/pkgwhat.clj"
if test -z "$1"
then
    targetbin=$HOME/bin
else
    targetbin=$1
fi
targ_pkgwhat=$targetbin/pkgwhat
dst_script=$targetbin/pkgwhat.clj

if ! test -d $targetbin
then
    echo $targetbin does not exist
    exit 1
fi

install -m 755 $src_script $dst_script

cat <<EOF > $targ_pkgwhat
#!/usr/bin/env bash
$dst_script \$(basename \$0) \$@
EOF

chmod 755 $targ_pkgwhat

(cd $targetbin
 for fname in which-package-has-file \
		  installed-packages \
		  files-in-package \
		  package-info
 do
     test -f $fname && rm $fname
     ln -s pkgwhat $fname
 done
)
