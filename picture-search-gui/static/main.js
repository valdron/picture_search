
Vue.component('button-counter', {
    data: function () {
        return {
            count: 0
        }
    },
    template: '<button v-on:click="count++">You clicked me {{ count }} times.</button>'
})

Vue.component('ps-picture', {
    props: ['picture', 'pictureStoreBaseUrl'],
    data: function () {
        return {
            count: 0,
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

Vue.component('ps-uploader', {
    data: function () {
        return {
        }
    },
    methods: {
        upload: function () {
            alert();
        }
    },
    template: `
        <div>
            <input type="file" accept="image/*">
            <button v-on:click=upload>Upload</button>
        </div>
        `
})

var app = new Vue({
    el: '#app',
    data: {
        picture: {
            id: "AWTg1S3e2vCljK26g0Ds",
            tags: [
                "abc",
                "abc def",
                "def"
            ],
            descriptionText: "abc def abc abc+abc",
            pictureId: "273c5543-4149-4eeb-b548-f2b27cbca619"
        }
    }
})
