pipeline {
    agent any

    environment {
        DRIVER_ENV = "DOCKER_REMOTE"
        ENV = "qa"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/remes1/saucedemo-ui-cucumber.git'
            }
        }

        stage('Run Tests (Docker Remote Grid)') {
            steps {
                withCredentials([
                    string(credentialsId: 'USERNAME', variable: 'USERNAME'),
                    string(credentialsId: 'PASSWORD', variable: 'PASSWORD'),
                    string(credentialsId: 'BLOCKED_USERNAME', variable: 'BLOCKED_USERNAME'),
                    string(credentialsId: 'BASE_URL', variable: 'BASE_URL')
                ]) {
                    sh '''
                        echo "Running regression tests on Docker Remote Grid..."
                        mvn clean test -Ddriver.env=DOCKER_REMOTE -Dcucumber.options="--tags @regression" -Dremote.hub.url=http://localhost:4444/wd/hub
                    '''
                }
            }
        }

        stage('Archive and Publish Report') {
            steps {
                archiveArtifacts artifacts: 'target/cucumber-report.html', fingerprint: true
                publishHTML([
                    reportName: 'Cucumber Report',
                    reportDir: 'target',
                    reportFiles: 'cucumber-report.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                ])
            }
        }
    }

    post {
        success {
            echo "Tests finished successfully!"
        }
        failure {
            echo "Some tests failed. Check Cucumber report."
        }
    }
}