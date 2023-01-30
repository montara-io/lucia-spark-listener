# Lucia Spark Listenr

### The New & Improved Spark UI and Spark History Server by Montara

Lucia spark listener is collecting metrics from Spark Jobs and send them to Lucia Backend Infrastructure

## Usage

1. Before running the job please setup Lucia on your environment (Local/Cloud) [for more information](https://github.com/montara-io/lucia-deployment)

2. After your Lucia environment is runinng, you can start use Lucia listener

### Fields:

1. endpoint

   - Cloud - tbd

   - Localenv - When you are running Lucia on your local env and running your are runing Spark job from cloud, you should use [Ngrok](https://ngrok.com/docs/getting-started):

     ```
     ngrok http 8181

     ```

     and set the endpoint Ngrok forwarding

   - Localenv - When you are running Lucia on your local env and running your Spark job from local,
     you should set the enpoint to http://localhost:8181

2. pipeline_id - tbd

3. pipeline_run_id - tbd

### Example:

```

spark-submit \
  --packages io.montara.lucia.sparklistener_2:12:add-github-pacakges-SNAPSHOT \
  --repositories https://maven.pkg.github.com/montara-io/lucia-spark-listener \
  --conf spark.lucia.sparklistener.url=<endpoint>/events \
  --conf spark.lucia.sparklistener.pipelineId=<pipeline_id> \
  --conf spark.lucia.sparklistener.pipelineRunId=<pipeline_run_id> \
  --conf spark.extraListeners=io.montara.lucia.sparklistener.LuciaSparkListener \
  <spark_job>

```
