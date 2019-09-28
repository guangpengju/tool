<template>
    <div :style="{height: ($store.state.common.innerHeight - 69 + 'px')}">
        <!-- 组件侧栏头部 -->
        <div class="widgets-layout-header">
            <!-- UI框架选择框 -->
            <div class="ui-choose">
                <el-dropdown @command="dropdownChange" trigger="click">
                    <label class="ui-choose-label">
                        {{useWidgets}}<i class="el-icon-arrow-down el-icon--right"/>
                    </label>

                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item v-for="item in widgetsList" :command="item.value">
                            {{item.label}}
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>

            <!-- 组件栏关闭按钮 -->
            <div class="ui-choose-close" @click="hidden">
                <mu-button small icon>
                    <mu-icon size="18" value=":el-icon-d-arrow-left"></mu-icon>
                </mu-button>
            </div>
        </div>

        <!-- 组件侧栏主体 -->
        <div style="padding-left: 3px">
            <el-collapse accordion>
                <el-collapse-item v-for="item in datas" :title="item.title">
                    <widgets-item :datas="item.datas"/>
                </el-collapse-item>
            </el-collapse>
        </div>
    </div>
</template>

<script>
    import WidgetsItem from '@/components/layout/WidgetsItem';
    export default {
        name: 'WidgetsLayout',
        componentName: 'WidgetsLayout',
        data: function () {
            return {
                useWidgets: 'ElementUI',
                widgetsList: [{
                    label: 'element-ui',
                    value: 'ElementUI'
                }, {
                    label: 'muse-ui',
                    value: 'MuseUI'
                }],
                datas: this.createWidgetsList()
            }
        },
        components: {
            WidgetsItem
        },
        methods: {
            createWidgetsList(){
                return [
                    this.loadWidgetsDatas('base','容器组件','base-assembly'),
                    this.loadWidgetsDatas('base1','容器组件','base-assembly')
                ]
            },
            loadWidgetsDatas(key, title, fileName) {
                let datas = {
                    key: key,
                    title: title,
                    datas: []
                };
                let jsonDatas = require('@/assets/datas/' + fileName + '.json');
                for (let k in jsonDatas) {
                    if( k != key) continue;

                    for (let i = 0; i < jsonDatas[key].length; i++) {
                        datas.datas.push(jsonDatas[key][i]);
                    }
                }
                return datas;
            },
            dropdownChange(command) {
                this.useWidgets = command;
                console.log(this.datas)
            },
            hidden(){
                this.$emit('hidden');
            }
        }
    };
</script>

<style scoped>
    .widgets-layout-header {
        width: 100%;
        height: 30px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 8px 0px;
    }

    .ui-choose {
        display: flex;
        align-items: center;
        margin-left: 20px;
    }

    .ui-choose-label {
        font-size: 14px;
        font-weight: bold;
        font-family: Roboto, Lato, sans-serif;
    }

    .ui-choose-close{
        display: flex;
        justify-content: flex-end;
        align-items: center;
    }
</style>



















