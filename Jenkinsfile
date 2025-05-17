pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/mihirchaple005/airline-management-system.git'
            }
        }

        stage('List Workspace') {
    steps {
        bat 'dir /s /b'
    }
}

        stage('Build Microservices') {
            parallel {
                stage('Build Flight Service') {
                    steps {
                        dir('flight-service') {
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
                stage('Build Ticket Service') {
                    steps {
                        dir('ticket-service') {
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
                stage('Build User Service') {
                    steps {
                        dir('user-service') {
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    bat 'docker-compose build'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    bat 'docker-compose down || exit 0'
                    bat 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        failure {
            echo 'Pipeline failed! Check logs.'
        }
    }
}