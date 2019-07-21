pipeline {
  agent {
    node {
      label 'master'
    }

  }
  stages {
    stage('clean') {
      steps {
        sh './gradlew clean'
      }
    }
    stage('compile') {
      steps {
        sh './gradlew compileReleaseSources'
      }
    }
    stage('upload') {
      steps {
        sh './gradlew uploadArchives'
      }
    }
  }
  options {
    skipStagesAfterUnstable()
  }
}