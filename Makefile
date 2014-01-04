clean:
	mvn clean

rds-authorize:
	aws rds authorize-db-security-group-ingress --db-security-group-name ec2 --cidrip `curl ifconfig.me/ip`/32

rds-revoke:
	aws rds revoke-db-security-group-ingress --db-security-group-name ec2 --cidrip `curl ifconfig.me/ip`/32

