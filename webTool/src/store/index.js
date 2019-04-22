import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        layout: [/*{
            id: 'test',//主键
            label:'',
            type: '',//类型 [assembly--组件;container--容器]
            class: '',//样式
            style: '',//样式
            showHtml: '',
            source: {
                html: '',//类型
                js: '',//类型
                css: ''//类型
            },


            tags: '',//标签
            evt: '',//事件
            props: '',//属性


            width: '',
            height: '',
            element: [],
            direction: '',
            'justify-content': '',
            'align-item': ''


            长
            宽
            样式
            对齐方式

            事件
            属性
        }*/]
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