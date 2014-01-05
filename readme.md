# KoobeGlobalEditor #

Web-based eBook Authoring Tool.

RD site:

    http://kooberd-globaleditor.elasticbeanstalk.com/


## Run GWT DevMode ##

    mvn clean package gwt:run

## After changes ##

    mvn compile

Or

    mvn package

## Repositories ##

    https://svn.koobe.com.tw:8443/svn/KoobeSystem3/KoobeGlobalEditor

## Deploy to BeansTalk ##

    mvn package beanstalk:upload-source-bundle beanstalk:create-application-version beanstalk:update-environment-options

## Git-Svn Usages ##

    git svn clone -r HEAD https://svn.koobe.com.tw:8443/svn/KoobeSystem3/KoobeGlobalEditor
    cd KoobeGlobalEditor
    git remote add github git@github.com:lyhcode/KoobeGlobalEditor.git
    git fetch github
    git merge github/master


    git svn rebase
    git svn dcommit
    git push github master
