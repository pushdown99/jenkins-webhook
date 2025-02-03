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
      securityContext:
        runAsUser: 1000
        runAsGroup: 1000
        fsGroup: 1000
Agents:
  Image:
    dockerImage:
''') {
    node(POD_LABEL) {
        stage("test") {
            container('java17') {
                sh "echo hello"
            }
        }
    }
} 

podTemplate(agentContainer: 'java17', cloud: 'kubernetes', namespace: 'jenkins', yaml: '''
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
      securityContext:
        runAsUser: 1000
        runAsGroup: 1000
        fsGroup: 1000
Agents:
  Image:
    dockerImage:
''') {
    node(POD_LABEL) {
    }
}

podTemplate(cloud: 'kubernetes', namespace: 'jenkins', yaml: '''
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
      securityContext:
        runAsUser: 1000
        runAsGroup: 1000
        fsGroup: 1000
Agents:
  Image:
    dockerImage:
''') {
    node(POD_LABEL) {
        stage("test") {
            container('java17') {
                sh "echo hello"
            }
        }
    }
} 




podTemplate(cloud: 'kubernetes', namespace: 'jenkins', yaml: '''
apiVersion: v1
kind: Pod
spec:
  containers:
    - name: jnlp
      image: .../inbound-agent:latest
      command: ""
      args: ""
    - name: java17
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
Agents:
  Image:
    dockerImage:
''') {
    node(POD_LABEL) {
        stage("test") {
            container('java17') {
                sh "echo hello"
            }
        }
    }
} 

podTemplate(yaml: '''
apiVersion: v1
kind: Pod
spec:
containers:

name: jnlp
image: jenkins/agent:4.6-1-jdk8-windowsservercore-1809
tty: true
name: powershell
image: 743808500811.dkr.ecr.us-east-1.amazonaws.com/core-net-powershell-azure-ad:latest
command:
powershell
args:
start-sleep
555
nodeSelector:
beta.kubernetes.io/os: windows
''') {
node(POD_LABEL) {
container('powershell') {
pwsh '-command Get-ChildItem Env: | Sort Name'
}
}
}
you can take 