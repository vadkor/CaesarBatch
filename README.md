Caesar Batch
============

A Spring Boot 2 / Spring Batch 4 application.

Encrypts given text file using the Caesar cipher. The cipher shifts all letters by 
a certain amount of positions. Other characters are unaffected. If the shift is 13 
then Caesar cipher is compatible with ROT-13.

# Prerequisites

Maven is needed to build the application.

# build and test:

    mvn package

# Run the application:

    java -jar target/caesar.batch-1.0-SNAPSHOT.jar --name=/your/path/to/FileToEncrypt.txt

(substitute /your/path/to/FileToEncrypt.txt with the full path to the file you want 
to encrypt)

Full list of command line arguments:

    --name : full name (including the path) to the file to encrypt. Mandatory.
    --threads : the number of threads to run for the batch processing. Default: 1.
    --shift : the number of letters to shift in the Caesar cipher run. Default: 13.
    --out : full name of the resulting encrypted file. Default: ./encrypted.txt

## Example:

    java -jar target/caesar.batch-1.0-SNAPSHOT.jar --name=/your/path/to/FileToEncrypt.txt --threads=5 --shift=1

# Output

The resulting encrypted file is the `encrypted.txt` file in the current directory or 
the file specified by the `--out` argument.
