function packageFunc (param) {
    alert(param)
}

export default {
    install: function (Vue) {
        Vue.prototype.global_func = (param) => packageFunc(param)
    }
}