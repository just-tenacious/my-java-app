pipeline {
    agent any

    tools {
        maven 'Maven3'  // Make sure this matches your Jenkins Maven installation name
    }

    triggers {
        githubPush()  // Requires GitHub webhook integration
    }

    stages {
        stage('Build and Test') {
            steps {
                // Use withMaven wrapper for better Maven integration
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
