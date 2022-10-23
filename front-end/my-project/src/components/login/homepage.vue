<template>
  <div>
    <header>
      <h1 class="title_name">YourCode</h1>
      <hr>
      <nav>

        <div class="navbar">
          <ul class="container gray highlightTextIn">

          </ul>

        </div>
      </nav>
    </header>

    <main>
      <!--      <img src="./Images/star.png" alt class="star">-->
      <el-image  :src="require('../../assets/Images/star.png')" class="star"></el-image>
      <article>
        <section>

          <div id="text">
            <div id="text2">
              <h2 id="create">CREATE</h2>
              <h1 id="title2">YOUR OWN QUESTION</h1>
              <p>A practice website for programming students </p>
              <p> where the exercises themselves are created by the students.</p>
            </div>
          </div>
          <div id="button">
            <span @click="func" >GET START</span>
          </div>
        </section>
        <br>
      </article>
    </main>

    <div class="card-container">
      <div class="card card-1">
        <div class="card-header" id="problems">
          Problems
        </div>
        <div class="card-body">
          <p>Over hundreds of questions ready to be solved, and daily updates on new exercises created. We also support the most popular languages Python, Java, and C. Practicing your problem solving skills with Your Code exercies and prepare for your interviews.</p>
        </div>
        <div class="card-button">
          <a @click="func3">View Problems</a>
        </div>
      </div>
      <div class="card card-2">
        <div class="card-header" id="create-card">
          Create
        </div>
        <div class="card-body">
          <p>Challenge others with your own exerciese. Your Code provides the platform for you to create your unique questions, where you can practice your creativity and knowledge against other students. Questions can vary in many different tags and topics, covering everything that a novice user may need. </p>
        </div>
      </div>
      <div class="card card-3">
        <div class="card-header" id="leader-board">
          Leader Board
        </div>
        <div class="card-body">
          <p>By creating and solving question you are competing aginst other users. There are three leader boards covering the best question created, top solutions, and top solver. You can compete by having the most ratings on the questions you create, solve, or having the most questions solved. </p>
        </div>
        <div class="card-button">
          <a @click="func4">View Leader Board</a>
        </div>
      </div>
      <div class="card card-4">
        <div class="card-header" id="about-us">
          About Us
        </div>
        <div class="card-body">
          <p>We are a small group of passionate developers from University of Auckland, and we want to help those who are experiencing the same difficulty we once did as a novice programmer.</p>
          <div class="card-button">
            <a @click="func2">View Leader Board</a>
          </div>
          <p>Email:</p>
          <p>ffen126@aucklanduni.ac.nz</p>
          <p>jyan836@aucklanduni.ac.nz</p>
          <p>pye048@aucklanduni.ac.nz</p>
          <p>tge000@aucklanduni.ac.nz</p>
          <p>twan740@aucklanduni.ac.nz</p>
          <p>wzha637@aucklanduni.ac.nz</p>
        </div>
      </div>
    </div>
  </div>

</template>
<script>

  export default {
    data () {
      return {
        username:'',
        show3: true
      }
    },
    created() {
      this.username = this.$store.state.user
    },
    mounted() {
      this.username = this.$store.state.user
    },
    methods: {
      func() {
        this.$router.push({ path: "Login"});
      },
      func2() {
        if(this.username === ''){
          this.$message.error("please login in first")
        }else{
          this.$router.push({ path: "problem"});
        }
      },
      func3() {
        if(this.username === ''){
          this.$message.error("please login in first")
        }else{
          this.$router.push({ path: "problem"});
        }
      },
      func4() {
        if(this.username === ''){
          this.$message.error("please login in first")
        }else{
          this.$router.push({ path: "leaderboard"});
        }
      }
    }
  }
</script>
<style>
  .transition-box {
    margin-bottom: 10px;
    width: 275px;
    height: 25px;
    border-radius: 4px;
    background-color: #409EFF;
    text-align: center;
    color: #fff;
    padding: 40px 20px;
    box-sizing: border-box;
    margin-right: 20px;
  }
  .login_form {
    padding-top: 10%;
    padding-left: 10%;
    padding-right: 10%;
  }
  .qxs-ic_user {
    background-size: 13px 15px;
    background-position: 3%;
  }
  .qxs-ic_password {
    background-size: 13px 15px;
    background-position: 3%;
    margin-bottom: 20px;
  }
  .login_logo {
    height: 100%;
  }
  .login_btn {
    width: 100%;
    font-size: 16px;
    background: -webkit-linear-gradient(left, #000099, #2154FA); /* Safari 5.1 - 6.0 */
    background: -o-linear-gradient(right, #000099, #2154FA); /* Opera 11.1 - 12.0 */
    background: -moz-linear-gradient(right, #000099, #2154FA); /* Firefox 3.6 - 15 */
    background: linear-gradient(to right, #000099 , #2154FA);
    filter: brightness(1.4);
  }
  /* logo of website */
  #logo {
    width: 80px;
    height: 80px;
    display: inline-block;
  }
  /* gray line in the header */
  hr{
    border:none;
    color:#000000;
    background-color: #000000;
    height: 1px;

  }
  header{
    position: relative;
  }
  header p{
    position:relative;
    float:right;
    display: block;
    margin-top: -40px;
    margin-right: 60px;
    font-family: Inter,sans-serif;
  }
  /*user icon*/
  .user{
    position: relative;
    margin-top: -50px;
    margin-right: 20px;
    float:right;
  }
  .logo{
    position: absolute;
    margin-top: -63px;
    margin-left: 20px;
  }

  /*full page*/
  * {
    padding: 0;
    margin: 0;
    text-decoration: none;
    list-style: none;
    box-sizing: border-box;
  }

  body {
    font-family: 'Open Sans',Arial,sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f8f8f8;
  }

  /* div for nav bar, the whole nav bar */
  .navBar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #ffffff;
  }

  .container li a
  {
    size: 100px;
    text-decoration: none;
    margin: 0px 10px;
    padding: 10px 10px;
    position: relative;
    z-index: 0;
    cursor: pointer;
  }

  /* Highlight text out  */
  .highlightTextIn li a:before
  {
    position: absolute;
    color: #000000;
    top: 0px;
    left: 0px;
    padding: 10px;
    overflow: hidden;
    content: attr(alt);
    transition: all 0.3s;
    transform: scale(0.8);
    opacity: 0;
  }

  .highlightTextIn li a:hover:before
  {
    transform: scale(1);
    opacity: 1;
  }

  /* Highlight text in  */
  .highlightTextIn li a
  {
    color: rgba(0, 0, 0, 0.4);
  }

  .highlightTextIn li a:before
  {
    transform: scale(1.2);
  }

  /* Homepage, Problems, Create, Leader Board  */
  ul {
    padding-top: 18px;
    padding-bottom: 15px;
    list-style-type: none;
    text-align: center;
  }

  ul li {
    display: inline-block;
    margin-right:30px;
  }
  ul li a {
    margin: 0 10px;
    border-radius: 10px;
    padding: 8px;
    font-size: 100px;
    color: #929292;
    position: relative;
    text-decoration: none;
  }

  ul li a::after{
    position: absolute;
    content: "";
    width: 0%;
    height: 4px;
    background-color: #929292;
    left: 50%;
    bottom: -1px;
    transition: all 0.4s ease-in-out;
  }

  .navBar:hover ul li a::after {
    width: 100%;
    left: 0;
  }


  /* all buttons on nav bar */
  .navbar ul li a{
    color: #929292;
    font-size:  15px;
    letter-spacing: 2px;
    margin: -8px;
  }


  /*star image*/
  .star{
    max-width: 2.5rem;
    object-fit: cover;
    margin-bottom: 2rem;
    margin-top:4rem;
    display: block;
    margin-left: auto;
    margin-right: auto;
  }
  /*star image spinning*/
  /* .star {
      max-width: 2.5rem;
      object-fit: cover;
      margin-bottom: 2rem;
      margin-top:4rem;
      display: block;
      margin-left: auto;
      margin-right: auto;
      animation: spin 2s infinite;
    }

    @keyframes spin {
      0% {
        transform: rotate(0deg);
      }
      100% {
        transform: rotate(360deg);
      }
    } */


  /* div for all text on home page */
  #text {
    font-size: 30px;
    letter-spacing: 3px;
  }
  #create{
    letter-spacing: 6px;
  }
  #text p {
    margin-top: 10px;
    display:block;
    font-size: 15px;
    text-transform: uppercase;
  }
  #text2 {
    text-align: center;
    margin-left: auto;
    margin-right: auto;
  }
  #title2 {
    font-size: 100px;
    color:rgb(0, 0, 0);
  }


  /* login/sign up buttons */
  #button{
    margin-top: 4rem;
    text-align: center;
  }
  #button span {
    color:#000000;
    border: 1px solid black;
    border-radius: 40px;
    padding: 12px 40px;
    margin-right: 5px;
    text-align: center;
    font-size: 1rem;
  }

  #button span:hover{
    background-color: rgb(0, 0, 0);
    color:#f8f8f8;
    transition: all 0.5s;
  }



  /* footer at bottom of page */
  #footer {
    text-align: center;
  }
  #footer p{
    color:#000000;
  }


  /* svg{
      margin-top: -500px;
  } */

  /* div for text on home page */
  div#text {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }



  /* login box */
  #login {
    padding: 40px 40px 20px 40px;
    border-radius: 50px;
    text-align: center;
    margin-top:80px;
    margin-bottom: 100px;
  }

  /* login box title buttons */

  #label{
    margin-bottom: 10px;
  }
  #label a:hover {
    background-color:#f8f8f8;
    transition: all 0.5s;
    border-radius: 10px;
  }

  /* username input for both login and sign up page */
  #username {
    border-radius: 20px;
    border: 10px solid transparent;
    cursor: pointer;
    transition: all 1s;
    margin: 10px 0px;
    width: 250px;
  }

  /* password input for both login and sign up page */
  #password {
    border-radius: 20px;
    border: 10px solid transparent;
    cursor: pointer;
    transition: all 1s;
    margin: 10px 0px;
    width: 250px;
  }

  /* email input for sign up page only*/
  #email {
    border-radius: 20px;
    border: 10px solid transparent;
    cursor: pointer;
    transition: all 1s;
    margin: 10px 0px;
    width: 250px;
  }

  /* submit button for both login and sign up page */
  #submit {
    border-radius: 20px;
    width: 250px;
    border: 1px solid #000000;
    color: #000000;
    background-color: #f8f8f8;
    cursor: pointer;
    margin: 10px 0px;
    padding: 12px 40px;
    margin-right: 5px;
    text-align: center;
    font-size: 1rem;
  }

  #submit:hover {
    background-color: #000000;
    color:#ffffff;
    transition: all 0.5s;
  }

  /* align all text in input bux to center */
  ::placeholder {
    text-align: center;
  }

  /* check box for remember me */
  #checkbox {
    text-align: center;
    color: #000000;
  }

  /* link to sign up page in check box */
  #checkbox a {
    color: #000000;
  }

  #checkbox a:hover {
    color: #000000;
    transition: all 0.5s;
  }
  input[type="text"]{
    padding:2px;
  }
  input[type="password"]{
    padding:2px;
  }




  /* div for profile icon and user's Username */
  #profile {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 0px 10px;
  }

  /* profile icon */
  div#profile img {
    width: 40px;
    height: 40px;
    cursor: pointer;
  }


  div#profile a:hover {
    background-color: #000000;
    transition: all 0.5s;
    border-radius: 7px;
  }

  /* div for welcome box on loggedin page */
  #welcome {
    background-color: #000000;
    margin-right: 140px;
    padding: 40px 40px 50px 40px;
    border-radius: 50px;
    text-align: center;
    font-size: 30px;
    color: #30a379;
  }

  /* link to profile on welcome box */
  #welcome a {
    color: rgb(49, 36, 60);
    border: 2px solid black;
    border-radius: 40px;
    padding: 7px;
    background-color: lightgreen;
  }

  #welcome a:hover {
    background-color:#000000;
    transition: all 0.5s;
  }

  .card-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    margin-top: 80px;
  }

  .card {
    width: 400px;
    margin: 15px 30px;
    border: 1px solid transparent;
    border-radius: 10px;
    padding: 15px;
    box-shadow: 0 0 15px  rgba(0, 0, 0, 0.15);
    display: block;
    width: 90%;
    margin-top: 20px;
  }

  .card-header {
    text-align: center;
    font-size: 33px;
    font-weight: bold;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  }

  .card-body {
    line-height: 1.5;
    font-size: 1.2em;
    padding: 15px;
    background-color: #fafafa;
  }

  #problems {
    color: rgb(252, 171, 10);
  }

  #leader-board {
    color: rgb(26, 197, 26);
  }

  #create-card {
    color: rgb(228, 16, 16);
  }

  #about-us {
    color: rgb(44, 29, 247);
  }

  .card-button {
    float: right;
    margin-right: 5px;
  }

  .card-button a {
    color: rgb(33, 165, 194);
    font-size: 18px;
  }

  .card-button a:hover {
    color: rgb(26, 105, 123);
  }
</style>
