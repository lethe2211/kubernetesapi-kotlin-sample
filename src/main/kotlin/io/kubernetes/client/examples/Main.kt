package io.kubernetes.client.examples

import io.kubernetes.client.openapi.ApiClient
import io.kubernetes.client.openapi.Configuration
import io.kubernetes.client.openapi.apis.CoreV1Api
import io.kubernetes.client.openapi.models.V1PodList
import io.kubernetes.client.util.ClientBuilder
import io.kubernetes.client.util.Config
import io.kubernetes.client.util.KubeConfig
import java.io.FileReader

fun main() {
    println("Start")

    // file path to your KubeConfig
    // val kubeConfigPath = "/Users/shuhei.shogen/.kube/config"

    // loading the out-of-cluster config, a kubeconfig from file-system
    // val client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(FileReader(kubeConfigPath))).build()
    val client = ClientBuilder.standard().build()

    // set the global default api-client to the in-cluster one from above
    Configuration.setDefaultApiClient(client)

    // the CoreV1Api loads default api-client from global configuration.
    val api = CoreV1Api()

    // invokes the CoreV1Api client
    val list: V1PodList = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null)
    println("Listing all pods: ")
    for (item in list.getItems()) {
        println(item.metadata?.name)
    }
}