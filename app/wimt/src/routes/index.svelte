<script lang="ts">
  import {Client} from "../lib/client";
  import Chart from "svelte-frappe-charts";

  let data;
  let chartRef;

  function onSubmit(e) {
    const formData = new FormData(e.target);
    const formDataData = {};
    for (let field of formData) {
      const [key, value] = field;
      formDataData[key] = value;
    }
    let client = new Client({
      username: formDataData["username"],
      password: formDataData["password"],
    });
    client.ready.then(() =>
        client.updateData().then((wastedTimes) => {
          console.log(wastedTimes);
          let labels = wastedTimes.map((time) => time.windowName);
          let datasets = wastedTimes.map((time) => time.wastedTime);
          data = {
            labels: labels,
            datasets: [
              {
                values: datasets,
              },
            ],
          };
        })
    );
  }
</script>

<svelte:head>
  <title>Home</title>
  <meta content="Svelte demo app" name="description"/>
</svelte:head>

<div>
  <h1>WASTED TIME CHART!!!</h1>
  <hr/>
  <div>
    <form on:submit|preventDefault={onSubmit}>
      <label for="username"> username </label>
      <input id="username" name="username" type="text"/>
      <br/>
      <label for="password"> password </label>
      <input id="password" name="password" type="password"/>
      <br/>
      <button type="submit">Poehali</button>
    </form>
  </div>
  <Chart bind:this={chartRef} {data} type="pie"/>
</div>
