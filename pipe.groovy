podTemplate(yaml: '''
apiVersion: v1
kind: Pod
spec:
  containers:
    - name: java17
      image: amazoncorretto:17-alpine-full
      command:
        - tail
      args:
        - -f
        - /dev/null
''') {
    node(POD_LABEL) {
        stage("test") {
            container('java17') {
                sh "java -version"
            }
        }
    }
}