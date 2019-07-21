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
        sh './gradlew :library:compileReleaseSources'
      }
    }
    stage('upload') {
      steps {
        sh './gradlew :library:uploadArchives'
      }
    }
  }
  options {
    skipStagesAfterUnstable()
  }
}