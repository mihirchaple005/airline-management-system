pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/mihirchaple005/airline-management-system.git'
            }
        }

        stage('Build Microservices') {
            parallel {
                stage('Build Flight Service') {
                    steps {
                        dir('FlightMicroservice') {  // Changed from flight-service
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
                stage('Build Ticket Service') {
                    steps {
                        dir('TicketMicroservice') {  // Changed from ticket-service
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
                stage('Build User Service') {
                    steps {
                        dir('UserMicroservice') {  // Changed from user-service
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
            try {
                sh 'docker-compose up -d'
            } catch (err) {
                echo "Deployment failed: ${err}"
                // Optional: currentBuild.result = 'UNSTABLE'
            }
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