podTemplate(
  yaml: '''
  apiVersion: v1
  kind: Pod
  spec:
    containers:
    - name: kaniko
      image: gcr.io/kaniko-project/executor:v1.6.0-debug
      imagePullPolicy: Always
      command:
      - sleep
      args:
      - 99d
      volumeMounts:
        - name: docker-config
          mountPath: /kaniko/.docker
      tty: true
    volumes:
        - name: docker-config
          configMap:
            name: docker-config-harbor
''') {
  node(POD_LABEL) {
    stage('Get a Kaniko project') {
      git url: 'https://github.com/pushdown99/jenkins-webhook.git', branch: 'main'
      container('kaniko') {
        stage('Build a Kaniko project') {
          sh '''
            echo "hello world"
          '''
        }
      }
    }
  }
}