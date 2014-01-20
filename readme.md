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

## 開發流程 ##

啟動 GWT DevMode

修改 Servlet Code .java 需要重新執行

    mvn clean gwt:run

修改程式碼重新編譯

修改 .ui.xml 不需要重新編譯，修改 GWT Client Code .java 需要重新編譯

    mvn compile

打包成 WAR 發佈

    mvn package

## 版號處理備忘 ##

* 這邊解決方式是在每次下 mvn 指令，加上強制設定版號
* mvn clean gwt:run -Dkoobe.application.version=1.0-SNAPSHOT
* mvn compile -Dkoobe.application.version=1.0-SNAPSHOT
* -D是複寫maven設定檔中的<property>設定值
* 在 Jenkins 上面也可以下 -D 來客製某些參數
* 預設版號是 timestamp
* 開發時要指定固定版號
* 在 Jenkins 上面會用 build / commit no 取代版號
* 從 local 發佈到 BeansTalk Version 是用 timestamp
* 從 jenkins 發佈到 BeasTalk 是用一致的 build / commit no