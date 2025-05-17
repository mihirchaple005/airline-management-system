pipeline {
    agent any

    stages {
        // Stage 1: Clone the GitHub repo
        stage('Checkout Code') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/mihirchaple005/airline-management-system.git'
            }
        }

        // Stage 2: Build all microservices
        stage('Build Microservices') {
            steps {
                sh 'mvn clean package -DskipTests'  // Build all services
            }
        }

        // Stage 3: Build Docker images
        stage('Build Docker Images') {
            steps {
                sh 'docker-compose build'
            }
        }

        // Stage 4: Deploy using Docker Compose
        stage('Deploy') {
            steps {
                sh 'docker-compose down || true'  // Stop old containers
                sh 'docker-compose up -d'        // Start new containers
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