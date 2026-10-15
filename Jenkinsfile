pipeline {
    agent any
    options {
        timeout(time: 30, unit: 'MINUTES')
    }

    parameters {
        choice(name: 'ENV', choices: ['dev', 'test', 'stage', 'prod'], description: 'Выбери стенд для сборки/деплоя')
        string(name: 'BRANCH', defaultValue: 'main', description: 'Git ветка для сборки')
        string(name: 'TAGS', defaultValue: 'no-tags', description: 'Теги/метки билда')

        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Браузер')
        string(name: 'BROWSER_VERSION', defaultValue: '141.0', description: 'Версия браузера')
        string(name: 'BROWSER_SIZE', defaultValue: '1920x1080', description: 'Размер окна браузера')
    }

    stages {
        stage('Set build name') {
            steps {
                script {
                    currentBuild.displayName = "${params.BRANCH} ${params.TAGS} [${params.ENV}]"
                }
            }
        }

        stage('Checkout') {
            steps {
                echo "Клонируем репозиторий из ветки: ${params.BRANCH}"
                checkout([$class: 'GitSCM',
                    branches: [[name: "*/${params.BRANCH}"]],
                    extensions: [[$class: 'WipeWorkspace']],
                    userRemoteConfigs: [[
                        url: 'git@github.com:org/repo.git',
                        credentialsId: 'your-cred-id'
                    ]]
                ])
            }
        }

        stage('Tests') {
            steps {
                echo "Запуск тестов: ENV=${params.ENV}, TAGS=${params.TAGS}, BROWSER=${params.BROWSER}"
                sh """
                  set -e
                  export ENV='${params.ENV}'

                  if [ '${params.TAGS}' = 'no-tags' ]; then
                    echo 'Запуск всех тестов без фильтрации по тегам'
                    ./gradlew TagsTask \
                      -Dbrowser='${params.BROWSER}' \
                      -DbrowserVersion='${params.BROWSER_VERSION}' \
                      -DbrowserSize='${params.BROWSER_SIZE}'
                  else
                    echo 'Запуск тестов с тегами: ${params.TAGS}'
                    ./gradlew TagsTask \
                      -DcustomTags='${params.TAGS}' \
                      -Dbrowser='${params.BROWSER}' \
                      -DbrowserVersion='${params.BROWSER_VERSION}' \
                      -DbrowserSize='${params.BROWSER_SIZE}'
                  fi
                """
            }
        }
    }

    post {
        always {
            echo "Публикуем Allure-отчёт"
            allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'build/allure-results']]
            ])
            echo "Чистим workspace"
            cleanWs()
        }
    }
}
