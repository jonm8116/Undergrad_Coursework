const routes = {
	'/'				: Front
	, '/login'		: Login
	, '/verify'		: Verify
	, '/register'	: Register
	, '/search'		: Search 
	, '/profile'	: Profile
}

const router = async() => {
	const content = null || document.getElementById('page-content');
	let request = Utils.parseRequestURL();
    let parsedURL = (request.resource ? '/' + request.resource : '/') + (request.id ? '/:id' : '') + (request.verb ? '/' + request.verb : '')
	let page = routes[parsedURL]
}

/* 
	- todo:
		- /additem : parent, mediaIds
		- GET /item/<id> : childType, parent, mediaIds 
		- DELETE /item/<id> : delete media items 
		- /item/<id>/like : 
		- /search : rank, parent, replies, hasMedia

*/