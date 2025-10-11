pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-cred')
    }

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/just-tenacious/my-java-app.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t myapp:latest .'
            }
        }

        stage('Push to DockerHub') {
            steps {
                bat """
                echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin
                docker tag myapp:latest shraddha15/myapp:latest
                docker push shraddha15/myapp:latest
                """
            }
        }

        stage('Deploy to Server') {
            steps {
                bat '''
                ssh -o StrictHostKeyChecking=no user@192.168.3.211 ^
                "docker pull shraddha15/myapp:latest && docker stop myapp || exit 0 && docker rm myapp || exit 0 && docker run -d -p 8080:8080 --name myapp shraddha15/myapp:latest"
                '''
            }
        }
    }

    post {
        success {
            echo '🚀 CDp Done: App deployed to server!'
        }
        failure {
            echo '❌ Deployment Failed! Check logs.'
        }
    }
}
