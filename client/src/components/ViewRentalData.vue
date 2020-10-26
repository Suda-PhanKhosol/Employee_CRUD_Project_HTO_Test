<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
       
      </v-flex>
    </v-layout>

    <div class="text-right">
      <v-col style="margin-left :1410px;"  cols="3">
       <v-row>
                  <b-button >
                       <router-link to="/"  ><v-btn  depressed large class="green " outlined color="black accent-2"   >Add New</v-btn></router-link>
                  </b-button>
                  <v-text-field style="margin-left :30px;"  v-model="EmployeeId" label="Employee ID" outlined></v-text-field>
                
                  <v-btn  style="margin-left :7px;"   depressed large class="black " outlined color="white accent-2"  @click="findEmpOK"  >Search</v-btn>
        </v-row>  
        </v-col>  
    </div>

    



    <v-data-table
    :headers="headers"
    :items="items"
    sort-by="calories"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
        flat
      >
        <v-toolbar-title>Employee Information</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog
          v-model="dialog"
          max-width="500px"
        >
         
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.name"
                      label="Name"
                    ></v-text-field>
                  </v-col>
                 
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.phone"
                      label="Phone"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.address"
                      label="Address"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
              
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue darken-1"
                text
                @click="close"
              >
                Cancel
              </v-btn>
              <v-btn
                color="blue darken-1"
                text
                @click="save"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">Are you sure you want to delete this item?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }" >
    
      <v-icon
        small
        class="mr-2"
        @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>

    <template  v-slot:item.status="{ item }">
          <v-container
            class="px-0"
            fluid
          >
            <v-switch
              v-model="item.status"
                inset
              
              @change="putStatus(item)"
            ></v-switch>
          </v-container>
  </template>
    
   
  </v-data-table>

  </v-container>
  
</template>

<script>
import http from "../http-common";

export default {
  name: "ViewRentalData",
  data() {
    
    return {
      
      headers: [
        { text: "ID", value: "id" },
        { text: "Name", value: "name" },
        { text: "Gender", value: "createdBy.name" },
        { text: "Phone", value: "phone" },
        { text: "Address", value: "address" },
       
        {
          text: "Admin",
          align: "left",
          sortable: false,
          value: "rentCustomer.name"
        },
         { text: "Action", value: 'actions', sortable: false },
          { text: "Work Status" ,value: 'status', sortable: false },
          
      ],
       items: [],
        desserts: [],
         switch1: true,
      editedIndex: -1,
      editedItem: {
        name: '',
        phone: '',
        address: ''
      },
      defaultItem: {
        name: '',
        phone: '',
        address: ''
      },
    };
  },

 computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'New Item' : 'Edit Infomation'
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
      dialogDelete (val) {
        val || this.closeDelete()
      },
      switch1(){
        this.putStatus()
      }
    },

    created () {
      this.initialize()
    },

  methods: {
    /* eslint-disable no-console */

    editItem (item) {
        this.editedIndex = this.items.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      deleteItem (item) {
        this.editedIndex = this.items.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },
      deleteItemConfirm () {
        http.delete("/delete/" +  this.editedItem.id).then(res => {
                console.log(res.data);
                this.getEmployees();
                //  alert("ลบข้อมูลเสร็จสิ้น");
            });
        this.items.splice(this.editedIndex, 1)
         alert("Delete Infomation Sucessful");  
        this.closeDelete()
        
      },
      updateActive() {
            var data = {
           
              name: this.editedItem.name,
              phone: this.editedItem.phone,
              address: this.editedItem.address,
            };
            http
              .put("/update/" + this.editedItem.id , data)
              .then(response => {
                console.log(response.data);
              })
              .catch(e => {
                console.log(e);
              });   
              alert("Update Infomation Sucessful");       
      },
      putStatus(item) {
         http
              .put("/update/status/" + item.id)
              .then(response => {
                console.log(response.data);
              })
              .catch(e => {
                console.log(e);
              });  
      },
      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },
      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },
      save () {
        if (this.editedIndex > -1) {
          Object.assign(this.items[this.editedIndex], this.editedItem)
        } else {
          this.items.push(this.editedItem)
        }
        this.updateActive()
        this.close()
       // this.$router.push('/view');
      },
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getEmployees();
    },
    findEmpOK() {
      http
        .get('/SearchEmployee/' + this.EmployeeId )
        .then(response => {
          console.log(response);
          if (response.data != []) {
                this.items = [response.data];
                console.log(this.items);
                //this.$router.push('/findBed');
          } else {
                this.items = [];
                console.log(this.items);
            this.$refs.form.reset();this.clear()
          }
        })
        .catch(e => {
          console.log(e);
        });
       
    },
    /* eslint-disable no-console */
  },
  mounted() {
    this.getEmployees();
  }
};


</script>
