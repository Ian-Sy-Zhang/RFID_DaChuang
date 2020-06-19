const baseUrl = '/api'
export default {

  // 个人中心
  Device: {
    getAllDevice: baseUrl + '/dataAdmin/Equipment/searchEquipmentList',
    addDevice: baseUrl + '/dataAdmin/Equipment/addEquipment',
    // 后加device id
    removeDevice: baseUrl + '/dataAdmin/Equipment/removeEquipment/'
  },
  Connect: {
    // 后加device id
    connect: baseUrl + '/dataAdmin/Equipment/setEquipmentOnline/',
    // 后加device id
    disConnect: baseUrl + '/dataAdmin/Equipment/setEquipmentOffline/'
  },
  RuleEngine: {
    addDataRule: baseUrl + '/ruleEngine/addDataRule',
    removeDataRule: baseUrl + '/ruleEngine/removeDataRule/',
    modifyDataRule: baseUrl + '/ruleEngine/modifyDataRule',
    getDataRules: baseUrl + '/ruleEngine/getDataRules',
    startDataRule: baseUrl + '/ruleEngine/startDataRule/',
    stopDataRule: baseUrl + '/ruleEngine/stopDataRule/',
    addEquipmentRule: baseUrl + '/ruleEngine/addEquipmentRule',
    removeEquipmentRule: baseUrl + '/ruleEngine/removeEquipmentRule/',
    modifyEquipmentRule: baseUrl + '/ruleEngine/modifyEquipmentRule',
    getEquipmentRules: baseUrl + '/ruleEngine/getEquipmentRules',
    startEquipmentRule: baseUrl + '/ruleEngine/startEquipmentRule/',
    stopEquipmentRule: baseUrl + '/ruleEngine/stopEquipmentRule/',
  }

}
