<template>
    <div class="widgets-item-main">
        <draggable :list="datas" :options="{fallbackOnBody: true}"
                   :group="{ name: 'assembly', pull: 'clone', put: false }" :sort="false" :clone="dataClone">
            <li class="widgets-item-cell" v-for="(item, index) in datas" :key="index">
                <a>
                    <i :class="['icon', item.icon?item.icon:'el-icon-info']"></i>
                    <span>{{item.label}}</span>
                </a>
            </li>
        </draggable>
    </div>
</template>

<script>
    import draggable from 'vuedraggable'

    export default {
        name: 'WidgetsItem',
        componentName: 'WidgetsItem',
        components: {
            draggable
        },
        props: {
            datas: Array
        },
        methods: {
            dataClone(original) {
                return this.common.createElement(original);
            }
        }
    };
</script>

<style scoped>
    .widgets-item-main {
        border-width: 1px 0 0 0;
        border-style: solid;
        border-color: #AAAAAA;
    }

    .widgets-item-cell {
        font-size: 12px;
        display: block;
        width: 48%;
        line-height: 30px;
        position: relative;
        float: left;
        left: 0;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        margin: 1%;
        border: 1px solid #F4F6FC;
    }

    .widgets-item-cell:hover {
        color: #409EFF;
        border: 1px dashed #409EFF;
    }

    .widgets-item-cell > a {
        display: block;
        cursor: move;
        color: #666666;
        background: #F4F6FC;
        border: 1px solid #F4F6FC;
    }

    a .icon {
        margin: 0px 3px 0px 5px;
        font-size: 14px;
        display: inline-block;
        vertical-align: middle;
    }

    a span {
        display: inline-block;
        vertical-align: middle;
    }
</style>



















