clean:
	mvn clean

rds-authorize:
	aws rds authorize-db-security-group-ingress --db-security-group-name koobe-globaleditor --cidrip `curl ifconfig.me/ip`/32

rds-revoke:
	aws rds revoke-db-security-group-ingress --db-security-group-name koobe-globaleditor --cidrip `curl ifconfig.me/ip`/32

deploy:
	mvn package beanstalk:upload-source-bundle beanstalk:create-application-version beanstalk:update-environment-options
