const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,
    css: {
        loaderOptions: {
            scss: {
                additionalData: `@import "~@/assets/styles/_variables.scss";`
            }
        }
    },
    devServer: {
        port: 3000,
        open: true
    }
})