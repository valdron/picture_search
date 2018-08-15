
Vue.component('ps-picture', {
    props: ['picture', 'pictureStoreBaseUrl'],
    data: function () {
        return {
        }
    },
    methods: {
        url: function () {
            return this.pictureStoreBaseUrl + this.picture.pictureId;
        }
    },
    template: `<div class="ps-picture">
                <dl>
                <dt>PictureId:</dt><dd>{{ picture.pictureId }}</dd>
                <dt>Tags:</dt><dd>{{ picture.tags }}</dd>
                <dt>Text:</dt><dd>{{ picture.descriptionText }}</dd>
                </dl>
                <img v-bind:src=url()> 
              </div>`
})

Vue.component('ps-query', {
    data: function () {
        return {
            query: "",
            pictureData: []
        }
    },
    methods: {
        search: function () {
            Vue.http.post("http://localhost:8082/pictures/query", this.query).then(response => {
                return response.json();
            }).then(data => {
                this.pictureData = data;
            });
        }
    },
    template: `<div>
               <form v-on:submit.prevent="search">
               <input v-model="query" placeholder="query"></input>
               <button type="submit">Search!</button>
               </form>
               <ps-picture v-for="picture in pictureData" :key="picture.pictureId" v-bind:picture="picture" picture-store-base-url="http://localhost:8081/pictures/"></ps-picture>
               </div>`
})

Vue.component('ps-uploader', {
    data: function () {
        return {
            file: {},
            tagsText: '',
            descriptionText: '',
        }
    },
    methods: {
        upload: function () {
            this.file = this.$refs.myFile.files[0];

            var config = {
                headers: {
                    'Content-Type': this.file.type,
                },
            }
            var tags = this.tagsText.split(',')
            tags.forEach(tag => {
                tag.trim();
            });
            Vue.http.post("http://localhost:8081/pictures/", this.file, config).then(response => {
                Vue.http.post("http://localhost:8082/pictures/", {
                    tags: tags,
                    descriptionText: this.descriptionText,
                    pictureId: response.data
                }).then(response => {
                    alert("Upload successful!");
                    this.$refs.myForm.reset();
                }, response => { alert("upload failed:" + response) });
            }, response => { alert("upload failed:" + response); });
        },
        onSubmit: function () { }
    },
    template: `
        <div>
        <form v-on:submit.prevent="onSubmit" ref="myForm" action="/">
            <input ref="myFile" type="file" accept="image/*">
            <input v-model="descriptionText" type="text" placeholder="description text">
            <input v-model="tagsText" type="text" placeholder="E.g. abc, def ghi, jkl, ...">
            <button type="submit "v-on:click=upload>Upload</button>
        </form>
        </div>
        `
})

var app = new Vue({
    el: '#app'
})
