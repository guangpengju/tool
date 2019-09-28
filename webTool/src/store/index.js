import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        common:{

        },
        layout: [],
        chooseData:{
            uuid: '0',
            value: {}
        }
    },
    mutations: {
        setCommon(state, data){
            state.common[data.key] = data.val;
        },
        setChooseData(state, data){
            state.chooseData[data.key] = data.val;
        }
    },
    actions: {
        setCommonVal(context, data){
            context.commit("setCommon", data);
        },
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