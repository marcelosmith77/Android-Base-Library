pipeline {
  agent {
    node {
      label 'master'
    }
  }
  options {
      // Stop the build early in case of compile or test failures
      skipStagesAfterUnstable()
  }
  stages {
    stage('clean') {
      steps {
        sh './gradlew clean'
      }
    }
    stage('compile') {
      steps {
        // Compile the app and its dependencies
        sh './gradlew compileReleaseSources'
      }
    }

    stage('upload') {
      steps {
        // upload archives to nexus
        sh './gradlew uploadArchives'
      }
    }
  }
}