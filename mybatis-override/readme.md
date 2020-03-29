mybatis的执行流程:

	1、执行查询需要sqlSession，sqlSession由sqlSessionFactory创建
	2、sqlSessionFactory中包含了我们查询需要的配置信息configuration对象，
		configuration对象中包含dataSource对象和Map对象mappedStatements,
		dataSource对象中存放的是我们连接数据库的连接属性，
		mappedStatements中key存放的是namespace.statementId(如：本例中insist.findById)
		mappedStatements中value存放的是mappedStatement对象,
		mappedStatement对象中包含了一条语句的id(即statementId),语句的入参,结果类型,statement类型和sql语句等
		statement类型分为statement、prepareStatement、callablestatement
	3、configuration中的对象值从配置文件中获取,其中包含全局配置文件和mapper配置文件
		全局配置文件中获取dataSource对象,
		mapper配置文件中获取mappedStatement对象,由于是多个对象所以存放在map集合中key为statementId,value为mappedStatement对象,
		解析mappedStatement对象涉及到对象的入参参数映射和查询结果类型的映射
	4.获取到sqlSession执行查询
		通过接口实现中的statementId从configuretion中查询到MappedStatement对象
			例如:(User user = sqlSession.selectOne("insist.findById", param);) 其中"insist.findById"为statementId
		通过configuration中获取dataSource对象,从dataSource中获取connection连接,
		通过mappedStatement对象获取statement类型进行相应的操作,封装入参和执行查询后的返回结果,返回用户
需要的表:
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8

