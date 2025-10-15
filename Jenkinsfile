pipeline {
    agent any
    
    tools {
        maven 'maven' // Configure in Jenkins Global Tool Configuration
        jdk 'java.home' // Configure in Jenkins Global Tool Configuration
    }
    
    environment {
        MAVEN_OPTS = '-Xmx1024m'
        //SONAR_HOST_URL = 'http://localhost:9000' // Optional: SonarQube URL
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm
                // Or use: git branch: 'main', url: 'https://github.com/your-repo/spring-security-app.git'
            }
        }
        
       stage('Build') {
    steps {
        echo 'Building Spring Security application...'
        dir('09-10-2025/Secure Task Management API using Spring Security/secure-task-api') {
            bat 'mvn clean compile -DskipTests'
        }
    }
}

        
        stage('Unit Tests') {
    steps {
        echo 'Running unit tests...'
        dir('09-10-2025/Secure Task Management API using Spring Security/secure-task-api') {
            bat 'mvn test'
        }
    }
    post {
    always {
        script {
            try {
                jacoco execPattern: '**/target/jacoco.exec'
            } catch (err) {
                echo "JaCoCo not available or failed: ${err.message}"
            }
        }
    }
}

        
        stage('Integration Tests') {
            steps {
                echo 'Running integration tests...'
                bat 'mvn verify -DskipUnitTests'
            }
        }
        
       stage('Code Quality Analysis') {
    steps {
        echo 'Analyzing code quality...'
        dir('09-10-2025/Secure Task Management API using Spring Security/secure-task-api') {
            script {
                try {
                    bat 'mvn checkstyle:checkstyle'
                } catch (Exception e) {
                    echo "Code quality check failed: ${e.message}"
                }
            }
        }
    }
    post {
        always {
            recordIssues enabledForFailure: true, tools: [checkStyle()]
        }
    }
}

        
        stage('Security Scan') {
    steps {
        echo 'Running security vulnerability scan...'
        dir('09-10-2025/Secure Task Management API using Spring Security/secure-task-api') {
            bat 'mvn dependency-check:check'
        }
    }
    post {
        always {
            dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
        }
    }
}

        
        stage('Package') {
    steps {
        echo 'Packaging application...'
        dir('09-10-2025/Secure Task Management API using Spring Security/secure-task-api') {
            bat 'mvn package -DskipTests'
        }
    }
    post {
        success {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
}
        
       stage('Deploy to Dev') {
    steps {
        echo 'Deploying to Development environment...'
        dir('09-10-2025/Secure Task Management API using Spring Security/secure-task-api') {
            script {
                bat '''
                    if not exist "C:\\Jenkins\\deployments" mkdir C:\\Jenkins\\deployments
                    copy target\\*.jar C:\\Jenkins\\deployments\\spring-security-app.jar
                '''
            }
        }
    }
}
        
        stage('Smoke Tests') {
            steps {
                echo 'Running smoke tests on deployment...'
                script {
                    sleep(time: 30, unit: 'SECONDS') // Wait for app to start
                    
                    // Using PowerShell's Invoke-WebRequest (built-in on Windows)
                    powershell '''
                        try {
                            $response = Invoke-WebRequest -Uri "http://localhost:8080/actuator/health" -UseBasicParsing -TimeoutSec 10
                            if ($response.StatusCode -eq 200) {
                                Write-Host "Health check passed!"
                                exit 0
                            } else {
                                Write-Host "Health check failed with status: $($response.StatusCode)"
                                exit 1
                            }
                        } catch {
                            Write-Host "Health check failed: $_"
                            exit 1
                        }
                    '''
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completed successfully!'
            emailext (
                subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                    <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
                recipientProviders: [developers(), requestor()]
            )
        }
        failure {
            echo 'Pipeline failed!'
            emailext (
                subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                    <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
                recipientProviders: [developers(), requestor()]
            )
        }
        always {
            cleanWs()
        }
    }
}
