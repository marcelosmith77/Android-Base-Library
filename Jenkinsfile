pipeline {
  agent {
    node {
      label 'android'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh './gradlew clean'
      }
    }
  }
}