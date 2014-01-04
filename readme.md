# KoobeGlobalEditor #

Web-based eBook Authoring Tool.

## Run GWT DevMode ##

    mvn clean package gwt:run

## After changes ##

    mvn compile

Or

    mvn package

## Repositories ##

    https://svn.koobe.com.tw:8443/svn/KoobeSystem3/KoobeGlobalEditor


## Git-Svn Usages ##

    git svn clone -r HEAD https://svn.koobe.com.tw:8443/svn/KoobeSystem3/KoobeGlobalEditor
    cd KoobeGlobalEditor
    git remote add github git@github.com:lyhcode/KoobeGlobalEditor.git
    git fetch github
    git merge github/master


    git svn rebase
    git svn dcommit
    git push github master