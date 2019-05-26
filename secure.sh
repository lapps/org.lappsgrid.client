#!/bin/bash

# This script adds encrypted environment variables to the .travis.yml
# file. The passwords and/or passphrases are read from the user's home directory
# so no private credentials are exposed by including this in SCM.
source $HOME/.passphrases

KEYS="GH_USER GH_PASS SONATYPE_USERNAME SONATYPE_PASSWORD PGP_PASSPHRASE ENCRYPTION_PASSWORD"
REPO=$(git remote get-url origin | cut -d: -f2 | sed 's/.git//')

function encrypt() {
	name=$1
	eval value=\$$name
	echo "Encrypting $name=$value"
	travis encrypt -r $REPO $name=\"$value\" --add
}

echo "Encoding keys for the repository $REPO"
for key in $KEYS; do
	encrypt $key
done

echo ".travis.yml updated."
