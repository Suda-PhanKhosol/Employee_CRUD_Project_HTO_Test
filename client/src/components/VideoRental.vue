<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Welcome to VideoRental</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                outlined
                label="Admin ID"
                v-model="employee.adminId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
              <p v-if="adminCheck != ''">Admin Name : {{adminName}}</p>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findAdmin" depressed large color="primary">Search</v-btn>
              </div>
            </v-col>
          </v-row>


          <v-col cols="10">
              <v-text-field
                outlined
                label="Name"
                v-model="employee.name"
                :items="name"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>

          <div >
            <v-row>
              <v-col cols="10">
                <v-select
                  label="Gender"
                  outlined
                  v-model="employee.genderId"
                  :items="employees"
                  item-text="name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>

            <v-col cols="10">
              <v-text-field
                outlined
                label="Phone"
                v-model="employee.phone"
                :items="phone"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>

             <v-col cols="10">
              <v-text-field
                outlined
                label="Address"
                v-model="employee.address"
                :items="address"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>

            
           
            <v-row justify="center">
              <v-col cols="12">
                <v-btn depressed large class="gray" outlined color="black" @click="saveEmployee" :class="{ yellow: !valid, green: valid }">Create</v-btn>
                
                 <b-button style="margin-left :10px;">
                     <router-link to="/view"  ><v-btn  depressed large class="gray" outlined color="black"   >View Data</v-btn></router-link>
                </b-button>
              </v-col>
            </v-row>
            <br />
          </div>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "employee",
  data() {
    return {
      employee: {
        adminId: "",
        genderId: "",
        name:"",
        phone:"",
        address:"",
  
      },
      name:[],
      phone:[],
      address:[],
      valid: false,
      adminCheck: false,
      adminName: ""
    };
  },
  methods: {
    /* eslint-disable no-console */


    getGender() {
      http
        .get("/gender")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    findAdmin() {
      http
        .get("/admin/" + this.employee.adminId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.adminName = response.data.name;
            this.adminCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    // function เมื่อกดปุ่ม submit
    saveEmployee() {
      http
        .post(
          "/employee/" + this.employee.name + "/" + this.employee.phone + "/" + this.employee.genderId + "/" + this.employee.address+ "/" + this.employee.adminId,
          this.employee
        )
        .then(response => {
          console.log(response);
          this.$router.push("/view");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
       alert("Create Infomation Sucessful");  
    },
    clear() {
      this.$refs.form.reset();
      this.adminCheck = false;
    },
    refreshList() {
      this.getGender();
  
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getGender();

  }
};
</script>
