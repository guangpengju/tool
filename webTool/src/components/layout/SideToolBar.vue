<template>
    <div class="side-toolbar-main">
        <div class="side-toolbar-item" :style="item.id == chooseId?'background: #c5c5c5;':''" v-for="item in btnList" @click="click(item.id)">
            <i :class="[item.icon?item.icon:'el-icon-set-up','side-toolbar-icon']"/>
            <span>{{item.name}}</span>
        </div>
    </div>
</template>
<script>
    export default {
        name: 'SideToolBar',
        componentName: 'SideToolBar',
        data(){
            return {
                chooseId: '',
            }
        },
        props:{
            btnList: Array,
        },
        methods: {
            click(id) {
                this.chooseId = this.chooseId == id?'':id;
                this.$emit('click', id, this.chooseId == id);
            }
        }
    };
</script>

<style scoped>
    .side-toolbar-main{
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        z-index: 10000;
        background: #eaeaea;
        border: 1px solid #AAAAAA;
        margin: 0px;
    }

    .side-toolbar-item{
        width: 100%;
        display: flex;
        justify-content: center;
        padding: 5px 0px;
        writing-mode: vertical-rl;
        cursor: pointer;
    }
    .side-toolbar-item:hover {
        background: #c5c5c5;
    }

    .side-toolbar-item>span{
        font-size: 13px;
        margin: 0px 3px 6px 3px;
    }

    .side-toolbar-icon{
        margin: 3px 5px;
        font-size: 14px;
        display: inline-block;
        vertical-align: middle;
    }
</style>





















