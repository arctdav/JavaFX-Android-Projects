## Assignment 1 Website
https://www.student.cs.uwaterloo.ca/~cs349/s20/assignments/a1.html

## Assignment 1 Output
**Without any input parameters**
```
no filename to find provided
  Usage java FindFiles filetofind [-option arg]
  -help                     :: print out a help page and exit the program.
  -reg                      :: find files using [filietofind] argument as a regular expression.
  -r                        :: execute the command recursively in subfiles.
  -dir [directory]          :: find the files the specified directory [directory]. Default directory is the calling directory.
  -ext [ext1,ext2,...]      :: find the files matching [filetofind] and with the given extensions [ext1, ext2,...]
```

**Input Parameters: -help**
```
  Usage java FindFiles filetofind [-option arg]
  -help                     :: print out a help page and exit the program.
  -reg                      :: find files using [filietofind] argument as a regular expression.
  -r                        :: execute the command recursively in subfiles.
  -dir [directory]          :: find the files the specified directory [directory]. Default directory is the calling directory.
  -ext [ext1,ext2,...]      :: find the files matching [filetofind] and with the given extensions [ext1, ext2,...]
```

**Input Parameters: .* -ext txt,java -reg -r**
```
looking for files .*.txt in directory project-dir\a1\.\.idea
looking for files .*.txt in directory project-dir\a1\.\out\production\a1
looking for files .*.txt in directory project-dir\a1\.\out\production
looking for files .*.txt in directory project-dir\a1\.\out
looking for files .*.txt in directory project-dir\a1\.\src
looking for files .*.txt in directory project-dir\a1\.
looking for files .*.java in directory project-dir\a1\.\.idea
looking for files .*.java in directory project-dir\a1\.\out\production\a1
looking for files .*.java in directory project-dir\a1\.\out\production
looking for files .*.java in directory project-dir\a1\.\out
looking for files .*.java in directory project-dir\a1\.\src
Found file: project-dir\a1\.\src\FindFiles.java
looking for files .*.java in directory project-dir\a1\.
```

**Input Parameters: .* -ext txt,java -reg -r -thisiswrong**
```
-thisiswrong is an invalid option. Please supply valid options from the following list...
  Usage java FindFiles filetofind [-option arg]
  -help                     :: print out a help page and exit the program.
  -reg                      :: find files using [filietofind] argument as a regular expression.
  -r                        :: execute the command recursively in subfiles.
  -dir [directory]          :: find the files the specified directory [directory]. Default directory is the calling directory.
  -ext [ext1,ext2,...]      :: find the files matching [filetofind] and with the given extensions [ext1, ext2,...]
```