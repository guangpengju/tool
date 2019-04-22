<template>
    <div class="full-screen">
        <draggable :class="['full-screen', 'draggable-main']" :list="elements" v-model="elements" group="assembly">
            <div v-for="(item,index) in elements" :key="item.id + '-' + index" v-html="item.showHtml"
                 @click="editInfo(item)"/>
        </draggable>

        <el-dialog title="组件编辑" :visible.sync="dialogFormVisible" :close-on-click-modal="false" top="5vh">
            <el-form label-position="left" label-width="120px" :model="form">
               <el-form-item label="组件宽度">
                    <el-slider v-model="form.width" :step="5" show-stops/>
                </el-form-item>
                <el-form-item label="组件高度">
                    <el-slider v-model="form.height" :step="5" show-stops/>
                </el-form-item>
                <el-form-item label="水平对齐方式">
                   <el-radio-group v-model="form.justify">
                       <el-radio label="flex-start">左对齐</el-radio>
                       <el-radio label="center">居中</el-radio>
                       <el-radio label="flex-end">右对齐</el-radio>
                       <el-radio label="space-between">两边对齐</el-radio>
                   </el-radio-group>
               </el-form-item>
               <el-form-item label="垂直对齐方式">
                   <el-radio-group v-model="form.align">
                       <el-radio label="flex-start">顶对齐</el-radio>
                       <el-radio label="baseline">居中</el-radio>
                       <el-radio label="flex-end">底对齐</el-radio>
                       <el-radio label="stretch">占满</el-radio>
                   </el-radio-group>
               </el-form-item>
               <el-form-item label="组件样式">
                   <el-input type="textarea" rows="5" v-model="form.style"/>
               </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    import draggable from 'vuedraggable'

    export default {
        name: 'Layout',
        componentName: 'Layout',
        components: {
            draggable
        },
        data() {
            return {
                dialogFormVisible: false,
                form: {

                }
            }
        },
        computed: {
            elements() {
                return this.$store.state.layout;
            }
        },
        methods: {
            editInfo(ele) {
                this.form = ele;
                this.dialogFormVisible = true;
            }
        }
    };
</script>

<style>
    .full-screen {
        height: 100%;
        width: 100%;
    }

    .draggable-main {
        background: #e9e7ee;
        flex-wrap: wrap;
        display: flex;
        align-items: flex-start;
    }
</style>





















