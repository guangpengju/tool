import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        layout: {
            type: 'container',
            name: 'mainContainer',
            attr: {
                style: {
                    custome: "height: 100%;width: 100%;background: white;"
                }
            },
            elements: []
        },
        chooseData:{
            uuid: '0',
            value: {}
        }
    },
    mutations: {
        setChooseData(state, data){
            state.chooseData[data.key] = data.val;
        }
    },
    actions: {
        setChooseDataUUID(context, val) {
            context.commit("setChooseData", {
                key: 'uuid',
                val: val
            });
        },
        setChooseDataValue(context, val) {
            context.commit("setChooseData", {
                key: 'value',
                val: val
            });
        }
    }
});