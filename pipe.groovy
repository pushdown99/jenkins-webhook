podTemplate(yaml: '''
apiVersion: v1
kind: Pod
spec:
  containers:
    - name: jnlp
      image: amazoncorretto:17-alpine-full
      command:
        - tail
      args:
        - -f
        - /dev/null
      securityContext:
        runAsUser: 1000
        runAsGroup: 1000
        fsGroup: 1000
''') {
    node(POD_LABEL) {
        stage("test") {
            container('jnlp') {
                sh "echo hello"
            }
        }
    }
}