
## Run GWT DevMode ##

    mvn package
    mvn gwt:run

## Repositories ##

https://svn.koobe.com.tw:8443/svn/KoobeSystem3/KoobeGlobalEditor


## Git-Svn Usages ##

    git clone git@github.com:lyhcode/KoobeGlobalEditor.git
    cd KoobeGlobalEditor
    git svn clone -r HEAD https://svn.koobe.com.tw:8443/svn/KoobeSystem3/KoobeGlobalEditor .
    git update-ref refs/remotes/git-svn refs/remotes/origin/master
    git svn dcommit
    git svn fetch
    git pull
