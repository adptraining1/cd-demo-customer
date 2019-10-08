node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/adptraining1/cd-demo-customer.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage("Sonar Qube Analyse") {
       def scannerHome = tool 'SonarQube Scanner 3.0';
        withSonarQubeEnv('Local SonarQube') {
            sh "${scannerHome}/bin/sonar-scanner"
        }
       
   }
   stage('Results') {
      archive 'target/*.jar'
   }
   
   stage('Push to CF') {
    pushToCloudFoundry cloudSpace: 'adp', credentialsId: '3c8d45c3-9168-46c0-ac8d-fad8eefa8f8c', organization: 'michael.ploed-org', selfSigned: 'true', target: 'target/cd-customer-0.1.0.jar'
  
   }
}
