import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        layout: {
            type: 'container',
            elements: []
        }
    },
    mutations: {
        add(state, ele) {
            ele.id = (ele.id + "-" + new Date());
            state.layout.push(ele);
        },
        remove(state, id) {
            if(state.layout.length < 1){
                let removeIndex;
                for (let i = 0; i < state.layout.length; i++) {
                    if (state.layout[i].id == id) {
                        removeIndex = i;
                        break;
                    }
                }

                state.layout.splice(removeIndex, 1);
            }
        },
        show(state) {
            console.log(state.layout)
        }
    },
    actions: {
        addElement(context, ele) {
            context.commit("add", ele);
        },
        removeElement(context, id) {
            context.commit("remove", id);
        }
    }
});