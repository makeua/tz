# tz

VM arguments needed for start:
-Dconfig="<path_to_config.xml>"
-Dlog4j.configuration="file:///<path_to_log4j.xml>"

To switch to in-memory db just update the "memory" to true value in config.xml
To recreate the schema set "drop" to true value in config.xml

It is useful to set both "memory" and "drop" for testing purposes.
For first start with "memory" false "drop" is necessary to create the schema.