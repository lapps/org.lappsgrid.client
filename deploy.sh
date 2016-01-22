#!/bin/bash
# DO NOT RUN THIS SCRIPT ON YOUR LOCAL MACHINE!!!
#
# This scipt WILL stomp all over any GPG keys you may already
# have installed.
#
# You have been warned.

# This script is called by Travis after the master branch build passes and
# does the following:
# 1. Downloads the encrypted copies of the GPG keys needed to sign artifacts.
# 2. Decrypts the keys to ~/.gnupg (this is the dangerous bit)
# 3. Creates the jars, javadocs, and sources. Signs everything and deploys to
#    oss.sonatype.org (either as a SNAPSHOT or to the staging repository).
# 4. Creates the Maven site and pushes this to the gh_pages branch in the
#    project repository.

# 1. Download the keys,
wget http://www.lappsgrid.org/keys/secring.gpg.enc
wget http://www.lappsgrid.org/keys/pubring.gpg.enc

# 2. Decrypt the keys.
openssl aes-256-cbc -d -pass pass:"$ENCRYPTION_PASSWORD" -in secring.gpg.enc -out ~/.gnupg/secring.gpg
openssl aes-256-cbc -d -pass pass:"$ENCRYPTION_PASSWORD" -in pubring.gpg.enc -out ~/.gnupg/pubring.gpg

# 3. Build and sign everything.
mvn -DskipTests=true package javadoc:jar source:jar deploy -Dgpg.passphrase="$PGP_PASSPHRASE" --settings settings.xml

# 4. Build and deploy the Maven site to the gh_pages branch.
mvn site --settings settings.xml
