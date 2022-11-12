
## About Base Repository

For the original web link see [here](https://github.com/pyroscope-io/otel-profiling-java/blob/main/README.md).

## About Current Repository

We think that Root Span alone cannot effectively identify the combined analysis of multiple application profiles under Request Scope, it is only suitable for profile analysis under a single application.

Although it is possible to obtain trace id + span id and global trace info in the Trace system, and then use the prfile_id in the profile system to determine the profiles of other applications under this request, but there is no doubt that it is very troublesome and needs to rely on Manually do metadata comparison and matching.

In order to associate profiles between multiple applications under one request, we enriched the metadata in the profile, modified the value of profile_id, added profile_trace_id, and added a service name to distinguish different instances of the same application.


### Based on the following points:

1. The trace id is global and unique, and can be associated with all applications.

2. The span id and application name can distinguish the related information of different applications in some different requests.

3. The instance name can distinguish the environment of different instances of the same service.


### Added configuration item list

- `otel.pyroscope.instance` - Instance name for profiler/baseline urls. If the instance name is not configured, it is automatically generated. It consists of the current IPV4 + a short UUID.

- `otel.pyroscope.add.trace.id` - Boolean flag. Controls whether the trace id added to profile labels. Default: `true`.

