<template>
    <div :style="{ height}">
        <div style="width: 100%;height: 30px;display: flex;justify-content: flex-end;margin-bottom: 10px">
            <div style="width: 60%;display: flex;align-items: center;margin-left: 10px;margin-top: 5px">
                <el-dropdown @command="dropdownChange" trigger="click">
                <span style="font-size: 14px;font-family: Roboto,Lato,sans-serif">
                {{useAssembly}}<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item v-for="item in assemblyItems" :command="item.value">{{item.label}}</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>

            <div style="width: 40%;display: flex;justify-content: flex-end;align-items: center">
                <mu-button small icon>
                    <mu-icon size="18" value=":el-icon-d-arrow-left"></mu-icon>
                </mu-button>
            </div>
        </div>

        <div style="padding-left: 3px">
            <el-collapse accordion>
                <el-collapse-item title="基础组件">
                    <assembly-item :datas="datas.base"></assembly-item>
                </el-collapse-item>
                <el-collapse-item title="容器">
                    12345678
                </el-collapse-item>
            </el-collapse>
        </div>
    </div>
</template>

<script>
    import AssemblyItem from '@/components/AssemblyItem'
    export default {
        name: 'Assembly',
        componentName: 'Assembly',
        data: function () {
            return {
                useAssembly: 'ElementUI',
                assemblyItems: [{
                    label: 'element-ui',
                    value: 'ElementUI'
                }, {
                    label: 'muse-ui',
                    value: 'MuseUI'
                }],
                datas: this.dataLoad()
            }
        },
        components: {
            AssemblyItem
        },
        props: {
            height: String
        },
        methods:{
            dataLoad(){
                let assemblyDatas = {};
                let baseAssembly = require('@/assets/datas/base-assembly.json');
                for (let key in baseAssembly) {
                    assemblyDatas[key] = [];
                    let assemblyArr = [];
                    for (let i = 0; i < baseAssembly[key].length; i++) {
                        assemblyArr.push(baseAssembly[key][i]);
                        if(i > 2 && i%3 == 0){
                            assemblyDatas[key].push(assemblyArr);
                            assemblyArr = [];
                        }
                    }
                    assemblyDatas[key].push(assemblyArr);
                }
                return assemblyDatas;
            },
            dropdownChange(command){
                this.useAssembly = command;
            }
        }
    };
</script>

<style scoped>

</style>



















