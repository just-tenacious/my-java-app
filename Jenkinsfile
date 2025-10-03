pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Build and Test') {
            steps {
                withMaven(maven: 'Maven3') {
                    bat 'mvn clean verify'
                }
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build and Tests SUCCESSFUL ✅'
        }
        failure {
            echo 'Build FAILED ❌'
        }
    }
}
