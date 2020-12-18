#!/bin/bash
echo "$1" | openssl rsautl -encrypt -inkey rsa_key.pub -pubin -out secret.dat

echo "`openssl rsautl -decrypt -inkey rsa_key.pri -in secret.dat`"
